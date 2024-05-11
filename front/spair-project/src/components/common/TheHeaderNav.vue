<template>
    <div id="header-container">
        <header>
            <div id="logo">SPAIR</div>
            <nav>
                <div id="tab">
                    <RouterLink :to="{ name: 'home' }">HOME</RouterLink> 
                    <RouterLink :to="{ name: 'board' }">게시판</RouterLink> 
                    <RouterLink :to="{ name: 'map' }">지도</RouterLink> 
                </div>
                    
                <!-- board 컴포넌트 추가 후 클릭 이벤트 수정 및 추가 예정-->
                <div id="search">
                    <label for="total-search">🔍</label>
                    <input type="text" id="total-search">
                    <button @click="event">통합검색</button>
                </div>

                <div id="user">
                    <RouterLink :to="{ name: 'join' }">회원가입</RouterLink> 
                    <RouterLink :to="{ name: 'login' }">로그인</RouterLink> 
                </div>
            </nav>
        </header>
    </div>
</template>

<script setup>
    // 테스트를 위한 임시 변수
    const event = function(){
        console.log("통합검색입니다.")
    }
</script>

<style scoped>
    #header-container {
        width: 100%; 
        height: 200px; 
        background-color: var(--primary-color);
        display:flex;
        justify-content: center;
        align-items: center;
        font-family: 'Tenada';
    }
    header {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        display: flex;
        justify-content: space-between;
    }
    #logo {
        font-size: 3rem;
        color: var(--secondary-color);
        font-weight: bold;
    }
    nav {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    nav div {
        margin: 10px;
    }
    #tab {
        min-width: 220px;
    }
    #tab a{
        font-size: 1.2rem;
        color: #FFFFFF;
        margin: 10px;
        text-decoration: none;
    }
    #search {
        font-size: 1.2rem;
        color: #000000;
        min-width: 400px;
        background-color: #FFFFFF;
        border-radius: 10px;
    }
    input {
        border-style: none;
        width: 280px;
        height: 30px;
    }
    button {
        border-style: none;
        height: 30px;
        background-color: #FFFFFF;
        font-family: 'Tenada';
        font-size: 1rem;
    }
    #user {
        min-width: 190px;
    }
    #user a{
        font-size: 1.3rem;
        color: #000000;
        margin: 10px;
        text-decoration: none;
    }
</style>