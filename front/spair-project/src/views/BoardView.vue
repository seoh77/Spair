<template>
    <div id="boardview-container">
        <div id="title">우리동네 PAIR 찾기 게시판</div>
        
        <RouterView/>
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
</style>