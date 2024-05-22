<template>
    <div id="each-comment">
        <div class="comment-info">
            <div class="comment-name">
                <div id="writer">{{ comment.user.nickname }}</div>
                <div id="replyBtn">답글</div>
            </div>
            <div class="date_wrap">
                <div v-if="!comment.modifiedDate" class="date">{{ comment.createdDate.replace("T", " ") }}</div>
                <div v-else class="date">{{ comment.modifiedDate.replace("T", " ") }}</div>
            </div>
        </div>
        <div class="comment-edit">
            <div id="content">{{comment.content}}</div>
            <!-- <div v-if="!isEditing[comment.commentId]" id="content">{{ comment.content }}</div>
            <textarea v-else v-model="comment.content" /> -->
            <div class="btn-area" v-if="loginUserId === comment.userId">
                <button id="update" v-if="!isEditing[comment.commentId]" @click="toggleEdit(comment.commentId)"></button>
                <button id="update-done" v-if="isEditing[comment.commentId]" @click="updateComment(comment)">완료</button>
                <button id="delete" @click="deleteComment(comment)"></button>
            </div>
        </div>
        <div v-for="comment in comment.replyComment" :key="comment.commnetId" class="reply-wrap">
            <Comment :comment="comment" reply="true"/>
         </div>
    </div>
</template>

<script setup>
    import { defineProps } from 'vue'

    const props = defineProps({
        comment: Object,
        reply: Boolean
    })


    // 댓글 삭제 기능
    // const deleteComment = function(comment) {
    //     const isDelete = confirm("정말 삭제하시겠습니까?")

    //     if(!isDelete) return

    //     axios.delete(`http://localhost:8080/api/comment/${comment.commentId}`)
    //     .then(() => {
    //         insertComment()
    //     })
    // }


    // 댓글 수정 기능 
    // const isEditing = ref({})
    // 수정란 토글 열기
    // const toggleEdit = (commentId) => {
    //     isEditing.value[commentId] = !isEditing.value[commentId]
    // }

    // const updateComment = function(comment) {
    //     axios.put(`http://localhost:8080/api/comment/${comment.commentId}`,{
    //         "content": comment.content,
    //         "status": 0
    //     })
    //     .then(() => {
    //         comments()
    //         isEditing.value[comment.commentId] = false
    //     })
    // }
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
</style>