<template>
    <div style="display: inline-block;width: 350px">
        <el-form label-width="100px">
            <el-form-item label="订单编号">
                <el-input v-model="ReturnOrder.Orderid" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用器材名称">
                <el-input v-model="ReturnOrder.Equipmentname" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户ID">
                <el-input v-model="ReturnOrder.Userid" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户名称">
                <el-input v-model="ReturnOrder.Username" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用开始时间">
                <el-input v-model="ReturnOrder.Renttime" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用结束时间">
                <el-input v-model="ReturnOrder.Endtime" readonly></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="Back">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                ReturnOrder:{
                    Orderid:'',
                    Equipmentname:'',
                    Userid:null,
                    Username:'',
                    Renttime:'',
                    Endtime:''
                }
            }
        },
        methods:{
            Back(){
                this.$router.push("/equipmentreturn");
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/equipment/getequipmentOrder/";
            axios.get(url + this.$route.query.id).then(function (resp) {
                _this.ReturnOrder = resp.data;
            })
        }
    }
</script>

<style scoped>

</style>