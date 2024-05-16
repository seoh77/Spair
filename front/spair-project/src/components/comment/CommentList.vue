<template>
    <div id="comment-container">
        <div id="wrap">
            <!-- API연결 후 v-for 수정 예정 -->
            <div id="each-comment" v-for="comment in filteredComments" :key="comment.id">
                <div>
                    <div id="writer">{{ comment.writer }}</div>
                    <div id="created-date">{{ comment.created_date }}</div>
                </div>
                <div>
                    <div id="content">{{ comment.content }}</div>
                    <button id="update" class="item"></button>
                    <button id="delete" class="item"></button>
                </div>
            </div>

            <CommentCreate/>
        </div>
    </div>
</template>

<script setup>
import CommentCreate from '@/components/comment/CommentCreate.vue';
import { useBoardStore } from '@/stores/board'
import { useRoute } from 'vue-router';
import { ref,computed } from 'vue'
const store = useBoardStore()
const route = useRoute()

// 임시 변수 및 메소드 . API 추가 후 수정 예정
const postId = Number(route.params.id);
const post = ref(null)
const filteredComments = computed(() => {
    return store.comments.filter(comment => comment.post_id == postId)
})

post.value = filteredComments.value[0];

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
        width: 90%;
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
    button {
        border-style: none;
        margin: 0.5rem;
    }
    #update {
        width: 2rem;
        height: 2rem;
        background-image: url('@/assets/update.png');
        background-size: cover;
    }
    #delete {
        width: 2rem;
        height: 2rem;
        background-image: url('@/assets/delete.png');
        background-size: cover;
    }
</style>