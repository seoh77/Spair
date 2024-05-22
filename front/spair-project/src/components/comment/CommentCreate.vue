<template>
    <div id="create-comment">
        <div id="input">
            <label for="insert">댓글</label>
            <textarea name="insert" id="insert" type="text" v-model="comment.content"></textarea>
        </div>
        <div id="btn">
            <button @click="commentCreate">등록</button>
        </div>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { onMounted, ref } from 'vue'
import axios from 'axios'
import { useCommentStore } from '@/stores/comment'
    
const store = useCommentStore()
const route = useRoute()

const comment = ref({
    postId: route.params.postId,
    userId: '',
    content: '',
    status: '',
})

const commentCreate= function(){
    axios.post('http://localhost:8080/api/comment', comment.value)
    .then(() => {
        store.insertComment(route.params.postId)
        comment.value.content = ''
    })
    .catch(error => {
        console.error(error)
    })
}

onMounted(() => {
    const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo"))
    comment.value.userId = localStorageData.userId
})

</script>

<style scoped>
    #create-comment {
        display: flex;
        justify-content: space-between;
        margin: 2rem 1.4rem;
    }

    label {
        margin: 0 1rem 0 0;
        font-weight: bold;
    }

    #input {
        display: flex;
        align-items: start;
        width: 90%;
    }

    textarea {
        width: 92%;
        resize: none;
        overflow-y: auto; 
        overflow-x: hidden;
        font-size: 1rem;
        padding: 5px ;
        font-family: 'NanumSquareRound';
    }

    #btn {
        display: flex;
        justify-content: center;
        align-items: start;
        width: 10%;
    }

    button {
        border-style: none;
        background-color: var(--primary-color);
        border-radius: 0.5rem;
        font-family: 'NanumSquareRound';
        font-size: 1.2rem;
        height: 2.3rem;
        width: 4rem;
    }
</style>