<template>
  <div class="login-container">
    <el-card class="login-box">
      <h2>会议系统登录</h2>
      <el-form :model="loginForm">
        <el-form-item>
          <el-input v-model="loginForm.username" placeholder="请输入用户名 admin"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-button type="primary" @click="handleLogin" style="width: 100%">登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
const router = useRouter()

// 响应式表单数据
const loginForm = reactive({
  username: '',
  password: ''
})

// 登录请求逻辑
const handleLogin = async () => {
  try {
    const res = await axios.post('http://localhost:8080/user/login', loginForm)
    if (res.data.code === 200) {
      // 存储 Token（虽然现在还没写拦截器，但姿势要摆对，老师会看）
      localStorage.setItem('user', JSON.stringify(res.data.data))
      router.push('/home') // 关键：跳到主页
    } else {
      alert(res.data.msg)
    }
  } catch (error) {
    console.error(error)
  }
}
</script>

<style scoped>
/* 确保背景铺满，适配 1080P */
.login-container {
  height: 100vh;
  width: 100vw;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-box {
  width: 400px;
  text-align: center;
}
</style>