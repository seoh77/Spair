<template>
    <div id="footer-container">
        <footer>
            <a href="/">개인정보처리방침</a>
            <a href="/">회사소개</a>
            <a href="/">고객센터</a>
            <a href="/">&copySPAIR</a>
        </footer>
    </div>
</template>

<script setup>
</script>

<style scoped>
    #footer-container {
        width: 100%;
        height: 130px;
        background-color: var(--secondary-color);
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: 'NanumSquareRound';
    }

    footer {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    a {
        font-size: 1.2rem;
        font-weight: bold;
        text-decoration: none;
        color: black;
        margin: 10px;
    }
</style>