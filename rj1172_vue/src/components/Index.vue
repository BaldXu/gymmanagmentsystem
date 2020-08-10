<template>
    <div style = "background-color: aliceblue">
        <el-container>
            <el-header>
                <el-row type="flex" justify="space-between">
                    <h3>广东海洋大学体育馆管理系统</h3>
                    <el-link :underline="false" style="color: white;"><h3>登录</h3></el-link>
                </el-row>
            </el-header>
            <el-container>
                <el-col :span="4">
                    <el-menu class="el-menu-vertical-demo"
                             @open="handleOpen"
                             @close="handleClose"
                             background-color="#545c64"
                             text-color="#fff"
                             active-text-color="#ffd04b"
                             router>
                        <el-submenu v-for="item in $router.options.routes" :index="item.name" v-if="item.show">
                            <template slot="title"><i :class="item.src"></i>{{item.name}}</template>
                            <el-menu-item v-if="item2.show" v-for="item2 in item.children" :index="item2.path">{{item2.name}}</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </el-col>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                activeIndex: '1',
            }
        },
        methods: {
            handleOpen(key, keyPath) {

            },
            handleClose(key, keyPath) {

            }
        }
    }
</script>

<style>
    .el-header{
        background-color: #545c64;
        border-radius: 5px;
        height: 150px;
    }

    .el-aside{
        border-radius: 5px;
        height: 150px;
    }

    .el-main{
        border-radius: 5px;
        height: 600px;
    }

    .el-row{
        color: white;
    }

    a{
        color: white;
        text-decoration: none;
    }
</style>
