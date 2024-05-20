<template>
    <div id="center-view">
        <h2>스포츠센터 페이지</h2>
        <BoardList />
    </div>
</template>

<script setup>
    import BoardList from '@/components/board/BoardList.vue'
    // import { ref, onMounted } from 'vue'
    // import { useRoute, useRouter } from 'vue-router';

    // const route = useRoute()

    // onMounted(()=> {
    //     console.log(route.params.centerId)
    // })
</script>

<style scoped>
    #center-view {
        width: 100%;
        display:flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    #center-view h2 {
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