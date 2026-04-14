<template>
  <div class="login-container">
    <el-card class="login-box">
      <h2>会议管理系统</h2>
      <el-form :model="loginForm">
        <el-form-item>
          <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="loginForm.password" type="password" placeholder="密码"></el-input>
        </el-form-item>
        <el-button type="primary" @click="handleLogin" style="width: 100%">登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactve } from 'vue' // 修正：应为 reactive
import { reactive } from 'vue' 
import axios from 'axios'

const loginForm = reactive({
  username: '',
  password: ''
})

const handleLogin = async () => {
  // 这里的 URL 要对应你后端的 8080 端口
  const res = await axios.post('http://localhost:8080/user/login', loginForm)
  if (res.data.code === 200) {
    alert('登录成功！')
  } else {
    alert(res.data.msg)
  }
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  width: 100vw;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); /* 渐变背景 */
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-box {
  width: 400px;
  text-align: center;
}
</style>