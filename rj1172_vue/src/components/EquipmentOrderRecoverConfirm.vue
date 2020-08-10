<template>
    <div style="display: inline-block;width: 350px">
        <el-form label-width="100px">
            <el-form-item label="订单编号">
                <el-input v-model="RecoverOrder.Orderid" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用器材名称">
                <el-input v-model="RecoverOrder.Equipmentname" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户ID">
                <el-input v-model="RecoverOrder.Userid" readonly></el-input>
            </el-form-item>
            <el-form-item label="用户名称">
                <el-input v-model="RecoverOrder.Username" readonly></el-input>
            </el-form-item>
            <el-form-item label="租用开始时间" v-if="this.$route.query.Orderstatus === '待确认'">
                <el-input v-model="RecoverOrder.Renttime" readonly></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="Confirm" v-if="this.$route.query.Orderstatus === '待确认'">确认回收</el-button>
                <el-button type="primary" @click="Examine" v-if="this.$route.query.Orderstatus === '待审核'">审核完成</el-button>
                <el-button type="info" @click="Back">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                RecoverOrder:{
                    Orderid:'',
                    Equipmentname:'',
                    Userid:null,
                    Username:'',
                    Renttime:''
                }
            }
        },
        methods:{
            Confirm(){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentOrderRecoverConfirm/";
                axios.put(url + this.$route.query.Id).then(function (resp) {
                    _this.$alert('确认完成!','消息',{
                        confirmButtonText: '确定',
                        callback: action => {
                            _this.$router.push("/equipmentorder");
                        }
                    })
                })
            },
            Examine(){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentOrderRentConfirm/";
                axios.put(url + this.$route.query.Id).then(function (resp) {
                    _this.$alert('审核完成!','消息',{
                        confirmButtonText: '确定',
                        callback: action => {
                            _this.$router.push("/equipmentorder");
                        }
                    })
                })
            },
            Back(){
                this.$router.push("/equipmentorder");
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/equipment/getequipmentOrder/";
            axios.get(url + this.$route.query.Id).then(function (resp) {
                _this.RecoverOrder = resp.data;
            })
        }
    }
</script>

<style scoped>

</style>
