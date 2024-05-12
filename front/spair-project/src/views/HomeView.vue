
<template>
  <main>
      <Intro/>
      <Advertisement/>
      <div id="title">
        최신 PAIR 찾기글
      </div>
       <!-- 추후 BoardView의 자식 컴포넌트(BoardList 등)를 구현해서 라우터로 연결할 예정 
        현 단계에서는 편의를 위해 BoardView에 임시로 페이지 구현 -->
      <BoardView/>
  </main>
</template>

<script setup>
import Intro from '@/components/home/Intro.vue'  
import Advertisement from '@/components/home/Advertisement.vue';
import BoardView from '@/views/BoardView.vue';
</script>

<style scoped>
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;  
  }
  #title {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    justify-content: start;
    align-items: center;
    font-family: 'Tenada';
    font-size: 2rem;
    margin: 40px;
  }
  main:nth-child(4) {
    width: 75%;
    max-width: 1200px;
    min-width: 1000px;
    display:flex;
    justify-content: center;
    align-items: center;
    font-family: 'Tenada';
    font-size: 2rem;
    margin: 40px;
  }
</style>