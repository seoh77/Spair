<template>
    <div id="header-container">
        <header>
            <div id="logo">
                <RouterLink :to="{ name: 'home' }">SPAIR</RouterLink> 
            </div>
            <nav>
                <div id="tab">
                    <RouterLink :to="{ name: 'home' }">HOME</RouterLink> 
                    <!--BoardView 대신 BoardList로 가도록 수정 -->
                    <RouterLink :to="{ name: 'boardList' }">게시판</RouterLink> 
                    <RouterLink :to="{ name: 'map' }">지도</RouterLink> 
                </div>
                    
                <!-- board 컴포넌트 추가 후 클릭 이벤트 수정 및 추가 예정-->
                <div id="search">
                    <label for="total-search">🔍</label>
                    <input type="text" id="total-search" v-model="searchQuery" @keyup.enter="search">
                    <button @click="search">통합검색</button>        
                </div>

                <div id="user">
                    <!-- <RouterLink :to="{ name: 'join' }">회원가입</RouterLink> 
                    <RouterLink :to="{ name: 'login' }">로그인</RouterLink>  -->
                    <RouterLink :to="{ name: 'join' }" v-if="!isLogin">회원가입</RouterLink> 
                    <RouterLink :to="{ name: 'login' }" v-if="!isLogin">로그인</RouterLink> 
                    <div class="login" v-if="isLogin"><div id="nickname">{{ nickname }}</div>님</div>
                    <button class="login" v-if="isLogin" @click="logout">로그아웃</button>
                </div>
            </nav>
        </header>
    </div>
</template>

<script setup>
    import { ref, onMounted, watch  } from 'vue'
    import { useBoardStore } from '@/stores/board'
    import { useRouter } from 'vue-router';
    const store = useBoardStore()
    const router = useRouter()
    
    
    const isLogin = ref(false)
    const nickname = ref('')
    
    
    // 로그인
    const userLogin = () =>{
        const userInfoStr = localStorage.getItem('loginUserInfo')
        if (userInfoStr) {
            isLogin.value = true
            const userIdInfo = JSON.parse(userInfoStr)
            nickname.value = userIdInfo.nickname
            router.push({ name: 'home'})
        }else {
            isLogin.value = false
            nickname.value = ''
            store.realLogin - false
        }
    }

    // 로그아웃 
    const logout = () => {
        localStorage.removeItem('loginUserInfo')
        isLogin.value = false
        nickname.value = ''
        store.realLogin = false
        router.push({ name: 'home' }) // 로그아웃 후 홈으로 이동
    }
    
    // onMounte 시 userLogin 호출
    onMounted(() => {
        userLogin()
    })
    
    // watch로 store의 reaLogin상태 변경 감시. realLogin 상태가 변경되면 userLogin호출
    // 직접적으로 localStorage를 감시할 수 없음
    watch(() => store.realLogin, () => {
        userLogin();
        console.log("로그인 상태 변경")
    })
        
            
    //search버튼 클릭 또는 enter시 boardSearchKeyword로 이동    
    const searchQuery = ref('')
    const search = function(){
        router.push({name: 'boardSearchKeyword', query: { search: searchQuery.value }})
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
        display: flex;
        align-items: center;
        font-size: 3rem;
        color: var(--secondary-color);
        font-weight: bold;
    }

    #logo a {
        text-decoration: none;
        color: var(--secondary-color);
        margin: 0;
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
        outline: none;
    }

    button {
        border-style: none;
        height: 30px;
        background-color: #FFFFFF;
        font-family: 'Tenada';
        font-size: 1rem;
    }

    #user {
        min-width: 160px;
        display: flex;
        justify-content: space-between
    }

    #user a{
        font-size: 1.3rem;
        color: #000000;
        margin: 0.1rem;
        text-decoration: none;
    }

    .login {
        font-size: 1.2rem;
        color: #000000;
        margin: 0.1rem 0;
        text-decoration: none;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: var(--primary-color);
    }

    #nickname {
        color: var(--secondary-color);
        margin: 0;
    }
</style>