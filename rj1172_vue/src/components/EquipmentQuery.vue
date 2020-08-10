<template>
    <div>
        <div>
            <el-form :inline="true" ref="Equipment">
                <h3 style="margin-top: 0;margin-bottom: 1px">购置新增</h3>
                <el-form-item label="器材名称">
                    <el-select v-model="NewEquipment.name" placeholder="器材名称">
                        <el-option v-for="(item,index) in EquipmentClass" :label="item.name" :value="item.name"></el-option>
                    </el-select>
                    <el-input-number v-model="num" :min="1" :max="10"></el-input-number>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="Submit">添加</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div style="margin: 0">
            <h3 style="margin-top: 0">器材查询</h3>
            <el-form :inline="true">
                <el-form-item label="器材名称">
                    <el-select v-model="Equipment.name" placeholder="选择器材">
                        <el-option v-for="(item,index) in EquipmentClass" :label="item.name" :value="item.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="器材状态">
                    <el-select v-model="Equipment.status" placeholder="选择器材状态">
                        <el-option label="空闲" value="空闲"></el-option>
                        <el-option label="租用中" value="租用中"></el-option>
                        <el-option label="维修中" value="维修中"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="Query">查询</el-button>
                    <el-button type="info" @click="Reset">重置</el-button>
                </el-form-item>
            </el-form>
            <div style="width: max-content">
                <el-table :data="EquipmentList" border style="width:max-content">
                    <el-table-column fixed prop="id" label="编号" width="250px"></el-table-column>
                    <el-table-column fixed prop="name" label="器材名称" width="200px"></el-table-column>
                    <el-table-column fixed prop="status" label="状态" width="150px"></el-table-column>
                    <el-table-column label="操作" width="300px">
                        <template slot-scope="scope">
                            <el-button @click="Rent(scope.row)" size="small" type="success" v-if="scope.row.status == '空闲'">租用</el-button>
                            <el-button @click="Fix(scope.row)" size="small" type="warning" v-if="scope.row.status == '空闲'">维修</el-button>
                            <el-button @click="Delete(scope.row)" size="small" type="danger" v-if="scope.row.status == '空闲' || scope.row.status == '维修中'">删除</el-button>
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
    </div>
</template>

<script>
    export default {
        data(){
            return{
                NewEquipment:{
                    name:'',
                },
                Equipment: {
                    name:'',
                    status:''
                },
                EquipmentList:[],
                EquipmentClass:[],
                total:null,
                num:1,
            }
        },
        methods:{
            Submit() {
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentedit/equipmentadd";
                axios.post(url,{name:this.NewEquipment.name,num:this.num}).then(function (resp) {
                    if (resp.data === "success") {
                        _this.$alert(_this.NewEquipment.name + _this.num + '个添加成功!', '消息', {
                            confirmButtonText: '确定',
                            callback:action => {
                                window.location.reload();
                            }
                        });
                    }
                })
            },
            Query(){
                const _this = this;
                const url = "http://localhost:8181/equipment/findAll/1/5/";
                if(this.Equipment.name == '' || this.Equipment.status == ''){
                    this.$alert('器材和器材状态不能为空!','消息',{
                        confirmButtonText: '确定',
                    })
                }
                else{
                    axios.get(url+this.Equipment.name+"/"+this.Equipment.status).then(function (resp) {
                        _this.EquipmentList = resp.data.content;
                        _this.total = resp.data.totalElements;
                    })
                }
            },
            Rent(row){
                this.$router.push({
                    path: '/equipmentrent',
                    query:{
                        id:row.id,
                    }
                })
            },
            Page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/equipment/findAll/"+currentPage+"/5/";
                axios.get(url+this.Equipment.name+"/"+this.Equipment.status).then(function (resp) {
                    _this.EquipmentList = resp.data.content;
                    _this.total = resp.data.totalElements;
                })
            },
            Reset(){
                window.location.reload();
            },
            Delete(row){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentedit/equipmentdelete/";
                axios.delete(url + row.id).then(function (resp) {
                    if(resp.data === "success"){
                        _this.$alert('删除成功!', '消息', {
                            confirmButtonText: '确定',
                            callback:action => {
                                window.location.reload();
                            }
                        });
                    }else{
                        _this.$alert('删除失败!该器材不存在!','消息',{
                            confirmButtonText: '确定',
                        })
                    }
                })
            },
            Fix(row){
                const _this = this;
                const url = "http://localhost:8181/equipment/equipmentedit/equipmentfix/";
                axios.put(url , {Id:row.id}).then(function (resp) {
                    if(resp.data === "success"){
                        _this.$alert('维修请求已发送!', '消息', {
                            confirmButtonText: '确定',
                            callback:action => {
                                window.location.reload();
                            }
                        });
                    }else{
                        _this.$alert('维修请求发送失败!该器材不存在!','消息',{
                            confirmButtonText: '确定',
                        })
                    }
                })
            },
        },
        created() {
            const _this = this;
            const url1 = "http://localhost:8181/equipment/findAll/1/5/";
            axios.get(url1).then(function (resp) {
                _this.EquipmentList = resp.data.content;
                _this.total = resp.data.totalElements;
            });

            const url2 = "http://localhost:8181/equipment/getequipment";
            axios.get(url2).then(function (resp) {
                _this.EquipmentClass = resp.data;
            })
        }
    }
</script>

<style scoped>

</style>
