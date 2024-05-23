<template>
    <div id="each-comment">
        <div class="private-comment" v-if="isPrivate"> 
            <img src="@/assets/lock.png" />
            <span>해당 댓글은 게시글 작성자와 댓글 작성자만 볼 수 있습니다.</span>
        </div>
        <div class="public-comment" v-if="!isPrivate">
            <div class="comment-info">
                <div class="comment-name">
                    <img src="@/assets/lock.png" v-if="!comment.status" class="lockIcon"/>
                    <div id="writer">{{ comment.user.nickname }}</div>
                    <div id="replyBtn" @click="clickReplyBtn(comment)">답글</div>
                </div>
                <div class="date_wrap">
                    <div v-if="!comment.modifiedDate" class="date">{{ comment.createdDate.replace("T", " ") }}</div>
                    <div v-else class="date">{{ comment.modifiedDate.replace("T", " ") }}</div>
                </div>
            </div>
            <div class="comment-edit">
                <div v-if="!comment.isEditing" id="content">{{ comment.content }}</div>
                <textarea v-else v-model="comment.content" />
                <div class="btn-area" v-if="userId === comment.userId">
                    <button id="update" v-if="!comment.isEditing" @click="toggleEdit(comment)"></button>
                    <button id="update-done" v-if="comment.isEditing" @click="updateComment(comment)">완료</button>
                    <button id="delete" @click="deleteComment(comment.commentId)"></button>
                </div>
            </div>
            <div v-for="comment in comment.replyComment" :key="comment.commnetId" class="reply-wrap">
                <Comment :comment="comment" />
            </div>
            <div class="reply_input_area" v-if="writeReply">
                <CommentCreate :parent="parent"  :change-write-reply="changeWriteReply"/>
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref, defineProps, onMounted } from 'vue'
    import axios from 'axios'
    import { useRoute } from 'vue-router'
    import { useCommentStore } from '@/stores/comment'
    import { useBoardStore } from '@/stores/board'
    import CommentCreate from '@/components/comment/CommentCreate.vue'
    
    const store = useCommentStore()
    const boardStore = useBoardStore()
    const route = useRoute()
    const writeReply = ref(false)
    const parent = ref()
    const isPrivate = ref(true)

    const props = defineProps({
        comment: Object
    })

    const userId = ref()

    onMounted(() => {
        const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo")) 
        userId.value = localStorageData.userId

        if(comment.status) {
            // 공개댓글인 경우
            isPrivate.value = false
        } else {
            // 비밀댓글인 경우 댓글 작성자와 게시글 작성자만 보임
            if(userId.value === props.comment.user.userId || userId.value === boardStore.board.user.userId) {
                isPrivate.value = false
            }
        }
    })

    // 댓글 삭제 기능
    const deleteComment = function(commentId) {
        const isDelete = confirm("정말 삭제하시겠습니까?")

        if(!isDelete) return

        axios.delete(`http://localhost:8080/api/comment/${commentId}`)
        .then(() => {
            store.insertComment(route.params.postId)
        })
    }


    // 수정 textarea 토글 열기
    const toggleEdit = (comment) => {
        comment.isEditing = !comment.isEditing
    }

    // 수정 기능
    const updateComment = function(comment) {
        axios.put(`http://localhost:8080/api/comment/${comment.commentId}`,{
            "content": comment.content,
            "status": 0
        })
        .then(() => {
            store.insertComment(route.params.postId)
            comment.isEditing = false
        })
    }

    const changeWriteReply = () => {
        writeReply.value = !writeReply.value
    }

    const clickReplyBtn = (comment) => {
        changeWriteReply()

        if(!comment.parentId) {
            parent.value = comment.commentId
        } else {
            parent.value = comment.parentId
        }
    }
</script>

<style scoped>
    #each-comment {
        display: flex;
        flex-direction: column;
        width: 100%;
    }

    .reply-wrap {
        width: 100%;
        margin-top: 20px ;
        padding-left: 3%;
        box-sizing: border-box ;
        width: 100%;
    }

    .comment-info {
        display: flex ;
        justify-content: space-between;
        margin-bottom: 5px ;
    }

    .comment-name {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #writer {
        font-size: 1.4rem;
        font-weight: bold;
        margin-right: 10px ;
    }

    #replyBtn {
        color: gray ;
        font-size: 0.7rem ;
        border: 1px solid gray;
        display: flex ;
        justify-content: center;
        align-items: center;
        padding: 0 5px ;
        border-radius: 5px ;
        height: 17px;
        cursor: pointer;
    }

    .date_wrap {
        width: 20%;
        display: flex ;
        justify-content: end;
    }

    .date {
        color: var(--gray-color);
        font-size: 1rem;
    }

    .comment-edit {
        display: flex ;
        align-items: center;
    }

    #content {
        width: 90%;
        font-size: 1.1rem;
        display: flex ;
        align-items: center ;
        white-space: pre-wrap;
        height: 25px;
    }

    .btn-area {
        width: 9%;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    button {
        border-style: none;
        margin: 0.4rem;
        background-color: #FFFFFF;
        cursor: pointer;
    }

    #update {
        width: 1rem;
        height: 1rem;
        background-image: url('@/assets/update.png');
        background-size: cover;
    }

    #delete {
        width: 1.5rem;
        height: 1.5rem;
        background-image: url('@/assets/delete.png');
        background-size: cover;
    }

    textarea {
        width: 88.3%;
        resize: none;
        overflow-y: auto; 
        overflow-x: hidden;
        font-size: 1rem;
        padding: 5px ;
        font-family: 'NanumSquareRound';
    }
    
    #update-done {
        width: 3rem;
        height: 2rem;
        font-family: 'NanumSquareRound';
        border-radius: 0.5rem;
        background-color: var(--secondary-color);
        margin: 0.4rem 0 0.4rem 0.4rem;
    }

    .private-comment {
        display: flex ;
        align-items: center ;
        color: var(--gray-color);
    }

    img {
        width: 1.1rem;
        margin-right: 5px ;
        margin-top: 3px;
    }

    .lockIcon {
        margin-bottom: 4px ;
    }
</style>