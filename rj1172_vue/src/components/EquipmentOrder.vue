<template>
    <div>
        <h3>器材订单</h3>
        <el-form :inline="true">
            <el-form-item label="订单编号">
                <el-input v-model="EquipmentOrder.orderid"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="QueryById">查询</el-button>
            </el-form-item>
            <el-form-item label="订单状态">
                <el-select v-model="EquipmentOrder.orderstatus" placeholder="器材状态">
                    <el-option label="租用中" value="租用中"></el-option>
                    <el-option label="待确认" value="待确认"></el-option>
                    <el-option label="已确认" value="已确认"></el-option>
                    <el-option label="待审核" value="待审核"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="QueryByStatus">查询</el-button>
                <el-button type="info" @click="Reset">重置</el-button>
            </el-form-item>
        </el-form>
        <div style="width: max-content">
            <el-table :data="EquipmentOrderList" border style="width:max-content">
                <el-table-column fixed prop="orderid" label="订单编号" width="200px"></el-table-column>
                <el-table-column fixed prop="equipmentname" label="器材名称" width="200px"></el-table-column>
                <el-table-column fixed prop="orderstatus" label="状态" width="150px"></el-table-column>
                <el-table-column fixed prop="userid" label="用户ID" width="150px"></el-table-column>
                <el-table-column fixed prop="renttime" label="订单开始时间" width="300px"></el-table-column>
                <el-table-column label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button @click="Check(scope.row)" size="small" type="primary" v-if="scope.row.orderstatus === '待确认' || scope.row.orderstatus === '待审核'">查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="display:flex;justify-content:center;">
                <el-pagination
                        background
                        layout="prev, pager, next"
                        :page-size="5"
                        :total="total"
                        @current-change="Page">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                EquipmentOrder: {
                    orderid:'',
                    orderstatus:'',
                },
                EquipmentOrderList:[],
                total:null,
            }
        },
        methods:{
            QueryById(){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentrecover/findorderbyid/1/5/";
                axios.get(url + this.EquipmentOrder.orderid).then(function (resp) {
                    _this.EquipmentOrderList = resp.data.content;
                    _this.total = 1;
                })
            },
            QueryByStatus(){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentrecover/findorderbystatus/1/5/";
                axios.get(url + this.EquipmentOrder.orderstatus).then(function (resp) {
                    _this.EquipmentOrderList = resp.data.content;
                    _this.total = resp.data.totalElements;
                })
            },
            Reset(){
                window.location.reload();
            },
            Check(row){
                this.$router.push({
                    path:"/equipmentorderrecoverconfirm",
                    query:{
                        Id:row.orderid,
                        Orderstatus:row.orderstatus,
                    }
                })
            },
            Page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentrecover/getorders/"+currentPage+"/5/";
                axios.get(url).then(function (resp) {
                    _this.EquipmentOrderList = resp.data.content;
                    _this.total = resp.data.totalElements;
                })
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/equipment/equipmentrecover/getorders/1/5";
            axios.get(url).then(function (resp) {
                _this.EquipmentOrderList = resp.data.content;
                _this.total = resp.data.totalElements;
            })
        }
    }
</script>

<style scoped>

</style>
