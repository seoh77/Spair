<template>
    <div id="comment-container">
        <div id="wrap">
            <div id="each-comment" v-for="comment in commentList" :key="comment.commentId">
                <div>
                    <!-- 넘어온 comment 데이터에서 user의 nickname이 없음. 
                    db에 접근/API 필요-->
                    <div id="writer">{{ comment.userId }}</div>
                    <div id="created-date">{{ comment.createdDate }}</div>
                </div>
                <div>
                    <div v-if="!comment.isEditing" id="content">{{ comment.content }}</div>
                    <textarea v-else v-model="comment.content" />
                    <div id="btn">
                        <button id="update"  v-if="!comment.isEditing" @click="toggleEdit(comment)"></button>
                        <button id="update-done" v-if="comment.isEditing" @click="updateComment(comment)">완료</button>
                        <button id="delete" @click="deleteComment(comment)"></button>
                    </div>
                </div>
            </div>

            <CommentCreate/>
        </div>
    </div>
</template>

<script setup>
import CommentCreate from '@/components/comment/CommentCreate.vue'
import axios from 'axios'

import { useBoardStore } from '@/stores/board'
import { useRoute, useRouter } from 'vue-router'
import { ref,computed, onMounted } from 'vue'
const store = useBoardStore()
const route = useRoute()
const router = useRouter()

// // 임시 메소드. API 연결 후 수정 예정
// // 댓글 수정 및 삭제
// const toggleEdit = (comment) => {
//   comment.isEditing = !comment.isEditing
// }
// const deleteComment = (comment) => {
//   // 댓글 삭제 로직 추가 예정
// }
// const updateComment = (comment) => {
//   // 댓글 수정 로직 추가 예정
//   // 수정 완료 후 isEditing을 false로 변경하여 다시 내용을 보이도록 설정
//   comment.isEditing = false;
// }


// 게시글에 해당하는 댓글 목록 조회 기능
const commentList = ref([])
onMounted(()=> {
    axios.get(`http://localhost:8080/api/comment/${route.params.postId}`)
    .then(response => {
        commentList.value = response.data
    })
    .catch(error => {
        console.error(error);
    });
})

// 댓글 삭제 기능
const deleteComment = function(comment) {
    axios.delete(`http://localhost:8080/api/comment/${comment.commentId}`)
    .then(() => {
        return axios.get(`http://localhost:8080/api/comment/${route.params.postId}`)
    })
    .then(response => {
        commentList.value = response.data
    })
    .catch(error => {
        console.error(error);
    });
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
    }
    #writer {
        width: 85%;
        font-size: 1.4rem;
        font-weight: bold;
    }
    #created-date {
        color: var(--gray-color);
        font-size: 1rem;
    }
    #content {
        width: 90%;
        font-size: 1.1rem;
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
        overflow-y: scroll; 
        overflow-x: hidden;
        font-size: 1.2rem;
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