<template>
    <div style="display: inline-block">
        <el-form label-width="100px">
            <el-form-item label="器材名称">
                <el-input v-model="EquipmentStandard.name" v-bind:readonly="readOnly"></el-input>
            </el-form-item>
            <el-form-item label="价格">
                <el-input v-model="EquipmentStandard.price"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" v-if="this.readOnly === true " @click="Modify">修改</el-button>
                <el-button type="primary" v-if="this.readOnly === false " @click="Add">添加</el-button>
                <el-button type="info" @click="Back">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                EquipmentStandard:{
                    name:'',
                    price:null
                },
                readOnly:true,
            }
        },
        methods:{
            Back(){
                this.$router.push("/equipmentstandard");
            },
            Modify(){
                const _this = this;
                const url = "http://localhost:8181/equipment/ModifyStandard";
                axios.put(url , this.EquipmentStandard).then(function (resp) {
                    _this.$alert('修改成功!','消息',{
                        confirmButtonText: '确定',
                        callback:action => {
                            _this.$router.push("/equipmentstandard")
                        }
                    })
                })
            },
            Add(){
                const _this = this;
                const url = "http://localhost:8181/equipment/AddStandard";
                axios.post(url , this.EquipmentStandard).then(function (resp) {
                    _this.$alert('添加成功!','消息',{
                        confirmButtonText: '确定',
                        callback:action => {
                            _this.$router.push("/equipmentstandard")
                        }
                    })
                })
            }
        },
        created() {
            this.readOnly = this.$route.query.status;
            if(this.readOnly === true){
                const _this = this;
                const url = "http://localhost:8181/equipment/findStandardByName/";
                axios.get(url + this.$route.query.name).then(function (resp) {
                    _this.EquipmentStandard.name = resp.data.name;
                    _this.EquipmentStandard.price = resp.data.price;
                })
            }
        }
    }
</script>

<style scoped>

</style>
