<template>
    <div id="create-comment">
        <div id="input">
            <label for="insert">댓글</label>
            <textarea name="insert" id="insert" type="text"></textarea>
        </div>
        <div id="btn">
            <!-- API 연결 후 click이벤트 추가 예정 -->
            <button>등록</button>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
#create-comment {
    display: flex;
    justify-content: space-between;
    margin: 2rem 1.4rem;
}
label {
    margin: 0 1rem 0 0;
    font-weight: bold;
}
#input {
    display: flex;
    align-items: start;
    width: 90%;
}
textarea {
    width: 92%;
    resize: none;
    overflow-y: scroll; 
    overflow-x: hidden;
    font-size: 1.2rem;
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
</style>