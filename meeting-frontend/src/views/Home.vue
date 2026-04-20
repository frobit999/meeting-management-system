<template>
  <el-container class="layout-container">
    <el-aside width="200px">
      <el-menu default-active="1" class="el-menu-vertical">
        <el-menu-item index="1">会议看板</el-menu-item>
        <el-menu-item index="2">会议管理</el-menu-item>
        <el-menu-item index="3">用户管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>会议报名管理系统 - 后台管理</el-header>
      <el-main>
        <el-empty description="欢迎回来！请开始你的管理工作" />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.layout-container { height: 100vh; }
.el-header { background-color: #f5f7fa; line-height: 60px; text-align: right; }
.el-aside { background-color: #304156; color: white; }
</style>