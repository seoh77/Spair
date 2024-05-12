<template>
    <div id="boardview-container">
        <!-- 추후 BoardView의 자식 컴포넌트(BoardList 등)를 구현해서 라우터로 연결할 예정 
        현 단계에서는 편의를 위해 BoardView에 임시로 페이지 구현 -->
        <h2>BoardView입니다.</h2>
    </div>
</template>

<script setup>
</script>

<style scoped>
    #boardview-container {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        display:flex;
        justify-content: center;
        align-items: center;
        font-family: 'Tenada';
        margin: 20px;
    }
</style>