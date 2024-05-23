<template>
    <div id="comment-container">
        <div id="wrap">
            <div class="comment-group" v-for="comment in store.commentList" :key="comment.commnetId"> 
                <Comment :comment="comment" :post-id="route.params.postId"/>
            </div>
            <CommentCreate />
        </div>
    </div>
</template>

<script setup>
    import Comment from '@/components/comment/Comment.vue'
    import CommentCreate from '@/components/comment/CommentCreate.vue'
    import { useRoute } from 'vue-router'
    import { ref, onMounted } from 'vue'
    import { useCommentStore } from '@/stores/comment'
    
    const store = useCommentStore()
    const loginUserId = ref()
    const route = useRoute()

    onMounted(()=> {
        store.insertComment(route.params.postId)
        
        const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo"))
        loginUserId.value = localStorageData.userId
    })
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

    .comment-group {
        margin: 0 1rem ;
        padding: 1rem 0.4rem ;
        box-sizing: border-box;
        border-bottom: 0.1rem dotted var(--gray-color);
    }
</style>