<template>
    <div id="comment-container">
        <div id="wrap">
            <!-- API연결 후 v-for 추가 예정 -->
            <div id="each-comment">
                <div>
                    <div id="writer">작성자</div>
                    <div id="created-date">작성일</div>
                </div>
                <div>
                    <div id="content">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Rerum, fugiat! Explicabo, dicta a. Dicta aspernatur dolorem saepe perferendis? Voluptate temporibus beatae quas quae quis repellat eius nesciunt asperiores sequi quam.</div>
                    <button id="update" class="item"></button>
                    <button id="delete" class="item"></button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
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
        margin: 1.2rem;
    }
    #each-comment > div {
        display: flex;
    }
    #writer {
        width: 90%;
        font-size: 1.5rem;
    }
    #created-date {
        color: var(--gray-color);
        font-size: 1rem;
    }
    #content {
        width: 90%;
        font-size: 1.2rem;
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