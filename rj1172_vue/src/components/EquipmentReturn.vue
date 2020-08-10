<template>
    <div>
        <h3>我的订单</h3>
        <div>
            <h4>未归还</h4>
            <div style="width: max-content">
                <el-table :data="N_MyOrderList" border style="width:max-content">
                    <el-table-column fixed prop="orderid" label="订单编号" width="200px"></el-table-column>
                    <el-table-column fixed prop="equipmentname" label="器材名称" width="200px"></el-table-column>
                    <el-table-column fixed prop="orderstatus" label="状态" width="150px"></el-table-column>
                    <el-table-column fixed prop="renttime" label="订单开始时间" width="300px"></el-table-column>
                    <el-table-column label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="Return(scope.row)" size="small" type="primary">归还</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="display:flex;justify-content:center;">
                    <el-pagination
                            background
                            layout="prev, pager, next"
                            :page-size="5"
                            :total="N_total"
                            @current-change="N_Page">
                    </el-pagination>
                </div>
            </div>
        </div>
        <div>
            <h4>待确认</h4>
            <div style="width: max-content">
                <el-table :data="C_MyOrderList" border style="width:max-content">
                    <el-table-column fixed prop="orderid" label="订单编号" width="200px"></el-table-column>
                    <el-table-column fixed prop="equipmentname" label="器材名称" width="200px"></el-table-column>
                    <el-table-column fixed prop="orderstatus" label="状态" width="150px"></el-table-column>
                    <el-table-column fixed prop="renttime" label="订单开始时间" width="300px"></el-table-column>
                    <el-table-column label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="Check(scope.row)" size="small" type="primary">查看</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="display:flex;justify-content:center;">
                    <el-pagination
                            background
                            layout="prev, pager, next"
                            :page-size="5"
                            :total="C_total"
                            @current-change="C_Page">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    export default {
        data(){
            return{
                N_MyOrderList:[],
                C_MyOrderList:[],
                N_total:null,
                C_total:null
            }
        },
        methods:{
            Return(row){
                this.$router.push({
                    path:'/equipmentreturncheck',
                    query:{
                        Id:row.orderid
                    }
                })
            },
            N_Page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/equipment/find_NOrderByUsername/"+currentPage+"/5/";
                let username = sessionStorage.getItem("user");
                axios.get(url + username).then(function (resp) {
                    _this.N_MyOrderList = resp.data.content;
                    _this.N_total = resp.data.totalElements;
                })
            },
            C_Page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/equipment/findOrderByUsername/"+currentPage+"/5/";
                let username = sessionStorage.getItem("user");
                axios.get(url + username).then(function (resp) {
                    _this.C_MyOrderList = resp.data.content;
                    _this.C_total = resp.data.totalElements;
                })
            },
            Check(row){
                this.$router.push({
                    path:'/equipmentreturnordercheck',
                    query:{
                        id:row.orderid
                    }
                })
            }
        },
        created() {
            const _this = this;
            const url1 = "http://localhost:8181/equipment/find_NOrderByUsername/1/5/";
            let username = sessionStorage.getItem("user");
            axios.get(url1 + username).then(function (resp) {
                _this.N_MyOrderList = resp.data.content;
                _this.N_total = resp.data.totalElements;
            })

            const url2 = "http://localhost:8181/equipment/find_COrderByUsername/1/5/";
            axios.get(url2 + username).then(function (resp) {
                _this.C_MyOrderList = resp.data.content;
                _this.C_total = resp.data.totalElements;
            })
        }
    }
</script>

<style scoped>

</style>
