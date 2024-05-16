<template>
    <div id="login-view">
        <h2>로그인</h2>
        <div id="login_form">
            <div>
                <label for="id">ID</label>
                <input type="text" name="id" id="id">
            </div>
            <div>
                <label for="password">PW</label>
                <input type="password" name="password" id="password">
            </div>
            <div class="btn_wrap">
                <button id="loginBtn" class="btn">로그인</button>
                <button id="joinBtn" class="btn">회원가입</button>
            </div>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
    #login-view {
        width: 80vw;
        margin-top: 50px ;
        display: flex;
        flex-direction: column;
        align-items: center
    }

    #login-view h2 {
        font-family: 'Tenada';
        font-size: 2rem;
    }

    #login_form {
        width: 80%;
        min-width: 600px;
        max-width: 1000px ;
        border: 1px solid var(--gray-color);
        box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
        margin-bottom: 100px ;
        padding: 7% 20%;
        box-sizing: border-box ;
        font-family: 'NanumSquareRound';
        font-size: 1.1rem ;
        font-weight: 600 ;
    }

    #login_form > div {
        display: flex ;
    }

    #login_form label {
        display: flex;
        width: 15%;
        margin-top: 7px ;
    }

    #login_form input {
        margin-left: 10px;
        width: 80%;
        height: 25px;
        border-radius: 5px;
        border: 1px solid black ;
        margin-bottom: 20px ;
    }

    .btn_wrap {
        display: flex ;
        justify-content: center;
        margin-top: 10px ;
    }

    .btn {
        width: 30%;
        height: 40px;
        border-radius: 5px ;
        border: none ;
        font-family: 'Tenada';
        font-weight: 600 ;
        font-size: 1.2rem ;
        margin-right: 10px ;
    }    

    #loginBtn {
        background-color: var(--primary-color);
    }

    #joinBtn {
        background-color: var(--secondary-color);
    }
</style>