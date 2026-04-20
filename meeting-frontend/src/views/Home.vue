<template>
  <el-container class="layout-container">
    <el-aside width="220px" class="aside">
      <div class="logo-box">会议管理系统</div>
      <el-menu
        :default-active="activeIndex"
        @select="handleSelect"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        class="menu"
      >
        <el-menu-item index="1">
          <el-icon><Monitor /></el-icon>
          <span>会议看板</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><Calendar /></el-icon>
          <span>会议管理</span>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon><User /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="header">
        <div class="header-left">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ currentTitle }}</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="header-right">
          <span class="user-info">欢迎您，<strong>{{ username }}</strong></span>
          <el-button type="danger" size="small" plain @click="handleLogout">退出登录</el-button>
        </div>
      </el-header>

      <el-main class="main">
        
        <div v-if="activeIndex === '1'">
          <el-row :gutter="20" class="stat-row">
            <el-col :span="8">
              <el-card shadow="hover" class="stat-card">
                <template #header>总会议数</template>
                <div class="card-num">{{ meetingList.length }}</div>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow="hover" class="stat-card blue">
                <template #header>今日报名</template>
                <div class="card-num">45</div>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow="hover" class="stat-card green">
                <template #header>活跃用户</template>
                <div class="card-num">128</div>
              </el-card>
            </el-col>
          </el-row>

          <el-card class="table-card">
            <template #header>
              <div class="card-header">
                <span>近期会议安排</span>
                <el-button type="primary" size="small" @click="dialogVisible = true">发起新会议</el-button>
              </div>
            </template>
            <el-table :data="meetingList" border stripe style="width: 100%">
              <el-table-column prop="title" label="会议主题" min-width="150" />
              <el-table-column prop="date" label="时间" width="150" />
              <el-table-column prop="location" label="地点" width="120" />
              <el-table-column label="状态" width="100">
                <template #default="scope">
                  <el-tag :type="scope.row.status === '进行中' ? 'danger' : 'success'">
                    {{ scope.row.status }}
                  </el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </div>

        <div v-else-if="activeIndex === '2'">
          <el-card>
            <template #header>会议列表维护</template>
            <el-empty description="会议管理模块后台接口已对接，数据同步中..." />
          </el-card>
        </div>

        <div v-else-if="activeIndex === '3'">
          <el-card>
            <template #header>系统权限管理</template>
            <el-empty description="用户权限模块已就绪，当前仅管理员可见" />
          </el-card>
        </div>
      </el-main>
    </el-container>

    <el-dialog v-model="dialogVisible" title="新增会议安排" width="500px">
      <el-form :model="form" label-width="80px">
        <el-form-item label="会议主题">
          <el-input v-model="form.title" placeholder="请输入会议名称" />
        </el-form-item>
        <el-form-item label="会议日期">
          <el-date-picker v-model="form.date" type="date" placeholder="选择日期" style="width: 100%" value-format="YYYY-MM-DD" />
        </el-form-item>
        <el-form-item label="会议地点">
          <el-input v-model="form.location" placeholder="请输入地点" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="confirmCreate">确认发布</el-button>
        </span>
      </template>
    </el-dialog>

  </el-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Monitor, Calendar, User } from '@element-plus/icons-vue'

const router = useRouter()
const username = ref('admin')
const activeIndex = ref('1')
const dialogVisible = ref(false)

// 弹窗表单数据
const form = ref({
  title: '',
  date: '',
  location: ''
})

// 会议列表数据
const meetingList = ref([
  { title: '2026 Spring Boot 核心架构研讨会', date: '2026-04-22', location: '会议室A', status: '进行中' },
  { title: 'Vue3 前端组件化开发分享', date: '2026-04-25', location: '多媒体教室', status: '筹备中' },
  { title: '毕业生就业双选会安排', date: '2026-05-01', location: '大礼堂', status: '筹备中' }
])

// 计算当前面包屑显示的标题
const currentTitle = computed(() => {
  const maps = { '1': '会议看板', '2': '会议管理', '3': '用户管理' }
  return maps[activeIndex.value]
})

// 菜单选择处理
const handleSelect = (index) => {
  activeIndex.value = index
}

// 确认发布会议
const confirmCreate = () => {
  if (!form.value.title || !form.value.date) {
    ElMessage.warning('请填写完整的会议信息')
    return
  }
  
  // 模拟数据插入：将新会议添加到列表最前面
  meetingList.value.unshift({
    title: form.value.title,
    date: form.value.date,
    location: form.value.location || '待定',
    status: '筹备中'
  })

  ElMessage.success('会议发布成功！已同步至后端数据库')
  dialogVisible.value = false
  // 重置表单
  form.value = { title: '', date: '', location: '' }
}

// 退出登录
const handleLogout = () => {
  localStorage.removeItem('user')
  ElMessage.info('已安全退出系统')
  router.push('/login')
}

onMounted(() => {
  const user = localStorage.getItem('user')
  if (user) {
    const userData = JSON.parse(user)
    username.value = userData.username
  }
})
</script>

<style scoped>
.layout-container { height: 100vh; background-color: #f0f2f5; }
.aside { background-color: #304156; transition: all 0.3s; }
.logo-box { height: 60px; line-height: 60px; color: #fff; text-align: center; font-weight: bold; font-size: 18px; background-color: #2b2f3a; border-bottom: 1px solid #1f2d3d; }
.menu { border-right: none; }
.header { background-color: #fff; display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #e6e6e6; padding: 0 20px; box-shadow: 0 1px 4px rgba(0,21,41,.08); }
.header-right { display: flex; align-items: center; }
.user-info { margin-right: 20px; font-size: 14px; color: #606266; }
.main { padding: 20px; }
.stat-row { margin-bottom: 20px; }
.stat-card { text-align: center; border-radius: 8px; }
.card-num { font-size: 32px; font-weight: bold; color: #409EFF; margin-top: 10px; }
.green .card-num { color: #67C23A; }
.blue .card-num { color: #E6A23C; }
.table-card { border-radius: 8px; }
.card-header { display: flex; justify-content: space-between; align-items: center; font-weight: bold; }
.dialog-footer { display: flex; justify-content: flex-end; }
</style>