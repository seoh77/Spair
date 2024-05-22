<template>
    <div id="create-comment">
        <div class="comment-title">
            <label for="insert">댓글</label>
            <img src="@/assets/lock.png" class="lockImg" v-if="isPrivata" @click="changePrivateStatus"/>
            <img src="@/assets/unlock.png" class="unlockImg" v-if="!isPrivata" @click="changePrivateStatus"/>
        </div>
        <div id="input">
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
    const isPrivata = ref(false)

    const props = defineProps({
        parent: Number,
        changeWriteReply: Function
    })

    const comment = ref({
        postId: route.params.postId,
        userId: '',
        content: '',
        status: ''
    })

    const changePrivateStatus = () => {
        isPrivata.value = !isPrivata.value
    }

    const commentCreate= function(){
        if(props.parent) {
            comment.value.parentId = props.parent
        }

        if(isPrivata.value) {
            comment.value.status = 0
        } else {
            comment.value.status = 1
        }

        axios.post('http://localhost:8080/api/comment', comment.value)
        .then(() => {
            store.insertComment(route.params.postId)
            comment.value.content = ''
            if(props.changeWriteReply) props.changeWriteReply()
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
        padding: 2rem 1rem;
        box-sizing: border-box ;
    }

    label {
        margin: 0 1rem 0 0;
        font-weight: bold;
    }

    #input {
        display: flex;
        align-items: start;
        width: 100%;
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

    .comment-title {
        display: flex;
        flex-direction: column ;
        width: 7% ;
        margin-left: 5px ;
    }

    img {
        margin-top: 5px;
        margin-left: 7px ;
        width: 1.3rem;
    }
</style>