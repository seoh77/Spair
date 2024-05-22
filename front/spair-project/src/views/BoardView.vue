<template>
    <div id="boardview-container">
        <div v-if="$route.path !== '/board/search'" id="title">우리동네 PAIR 찾기 게시판</div>
        
        <RouterView/>
        <div v-if="$route.path == '/board'" id="link">
            <RouterLink :to="{ name: 'boardCreate' }">게시글 등록</RouterLink>
        </div>
    </div>
</template>

<script setup>
import { RouterView, RouterLink } from 'vue-router';

</script>

<style scoped>
    #boardview-container {
        width: 100%;
        display:flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    #title {
        font-size: 2rem;
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        display:flex;
        justify-content: start;
        align-items: center;
        font-family: 'Tenada';
        margin: 2rem;
    }
    #link {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        display: flex;
        flex-direction: row-reverse;
    }
    #link a {
        width: 15%;
        height: 2.4rem;
        font-family: 'NanumSquareRound';
        font-weight: bold;
        font-size: 1.4rem;
        text-align: center;
        text-decoration: none;
        background-color: var(--primary-color);
        color: #FFFFFF;
        border-radius: 0.5rem;
        margin: 2rem 0;
        padding: 0.3rem 0 0 0;
    }
</style>