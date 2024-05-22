<template>
    <div id="comment-container">
        <div id="wrap">
            <div class="comment-group" v-for="comment in commentList" :key="comment.commnetId"> 
                <div class="line"></div>
                <Comment :comment="comment" />
            </div>
            <!-- emit 이벤트-->
            <CommentCreate @new-comment="comments"/>
        </div>
    </div>
</template>

<script setup>
    import Comment from '@/components/comment/Comment.vue'
    import CommentCreate from '@/components/comment/CommentCreate.vue'
    import axios from 'axios'
    import { useRoute } from 'vue-router'
    import { ref, onMounted } from 'vue'
    
    const loginUserId = ref()
    const route = useRoute()

    onMounted(()=> {
        insertComment()
        
        const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo"))
        loginUserId.value = localStorageData.userId
    })

     // 게시글에 해당하는 댓글 목록 조회 기능
    const commentList = ref([])
    const insertComment = async() => {
        const response = await axios.get(`http://localhost:8080/api/comment/${route.params.postId}`)

        const allComment = response.data 
        let commentIdx = -1
        let replyIdx

        allComment.forEach((comment) => {
            comment.isEditing = false
            // 부모 댓글인 경우 commentList에 추가
            if(!comment.parentId) {
                commentList.value.push(comment)
                commentList.value[++commentIdx].replyComment = {}
                replyIdx = 0
            } else {
                // 답글인 경우 부모댓글의 replyComment 요소에 추가
                commentList.value[commentIdx].replyComment[replyIdx++] = comment
            }
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

    .comment-group {
        padding: 1rem 1.4rem ;
        box-sizing: border-box;
    }
</style>