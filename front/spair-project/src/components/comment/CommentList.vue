<template>
    <div id="comment-container">
        <div id="wrap">
            <div id="each-comment" v-for="comment in commentList" :key="comment.commentId">
                <div>
                    <!-- 넘어온 comment 데이터에서 user의 nickname이 없음. 
                    db에 접근/API 필요-->
                    <div id="writer">{{ comment.user.nickname }}</div>
                    <div v-if="!comment.modifiedDate" class="date">{{ comment.createdDate.replace("T", " ") }}</div>
                    <div v-else class="date">{{ comment.modifiedDate.replace("T", " ") }}</div>
                </div>
                <div>
                    <!-- <div v-if="!comment.isEditing" id="content">{{ comment.content }}</div> -->
                    <div v-if="!isEditing[comment.commentId]" id="content">{{ comment.content }}</div>
                    <textarea v-else v-model="comment.content" />
                    <div id="btn" v-if="loginUserId === comment.userId">
                        <button id="update"  v-if="!isEditing[comment.commentId]" @click="toggleEdit(comment.commentId)"></button>
                        <button id="update-done" v-if="isEditing[comment.commentId]" @click="updateComment(comment)">완료</button>
                        <button id="delete" @click="deleteComment(comment)"></button>
                    </div>
                </div>
            </div>
            <!-- emit 이벤트-->
            <CommentCreate @new-comment="comments"/>
        </div>
    </div>
</template>

<script setup>
import CommentCreate from '@/components/comment/CommentCreate.vue'
import axios from 'axios'

import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
const route = useRoute()
const loginUserId = ref()


// 게시글에 해당하는 댓글 목록 조회 기능
const commentList = ref([])
const comments = () => {
    axios.get(`http://localhost:8080/api/comment/${route.params.postId}`)
    .then(response => {
        commentList.value = response.data
        response.data.forEach((comment) => {
            isEditing.value[comment.commentId] = false
        })
    })
    .catch(error => {
        console.error(error)
    })
}

onMounted(()=> {
    comments()
    
    const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo"))
    loginUserId.value = localStorageData.userId
})


// 댓글 삭제 기능
const deleteComment = function(comment) {
    const isDelete = confirm("정말 삭제하시겠습니까?")

    if(!isDelete) return

    axios.delete(`http://localhost:8080/api/comment/${comment.commentId}`)
    .then(() => {
        return axios.get(`http://localhost:8080/api/comment/${route.params.postId}`)
    })
    .then(response => {
        commentList.value = response.data
        response.data.forEach((comment) => {
            isEditing.value[comment.commentId] = false;
      })
    })
    .catch(error => {
        console.error(error);
    });
}


// 댓글 수정 기능 
const isEditing = ref({})
// 수정란 토글 열기
const toggleEdit = (commentId) => {
   isEditing.value[commentId] = !isEditing.value[commentId]
}

const updateComment = function(comment) {
    axios.put(`http://localhost:8080/api/comment/${comment.commentId}`,{
        "content": comment.content,
        "status": 0
    })
    .then(() => {
        comments()
        isEditing.value[comment.commentId] = false
    })
}

</script>

<style scoped>
    #comment-container {
        width: 100%;
        display: flex;
        flex-direction: column;
        font-size: 1.2rem;
        font-family: 'NanumSquareRound';
    }

    #wrap {
        border: 0.1rem solid var(--gray-color);
        box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
        margin: 0.8rem 0.5rem ;
    }

    #each-comment {
        display: flex;
        flex-direction: column;
        margin: 1.4rem;
    }
    
    #each-comment > div {
        display: flex;
        margin-bottom: 5px;
    }

    #writer {
        width: 85%;
        font-size: 1.4rem;
        font-weight: bold;
    }

    .date {
        color: var(--gray-color);
        font-size: 1rem;
        width: 20%;
    }

    #content {
        width: 90%;
        font-size: 1.1rem;
        display: flex ;
        align-items: center ;
        white-space: pre-wrap;
    }

    #btn {
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
        width: 1.3rem;
        height: 1.3rem;
        background-image: url('@/assets/update.png');
        background-size: cover;
    }

    #delete {
        width: 2rem;
        height: 2rem;
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