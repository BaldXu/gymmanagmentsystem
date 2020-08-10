<template>
    <div>
        <h3>器材租用消费标准
            <el-button @click="Add" size="medium" type="primary">增添</el-button>
        </h3>

        <div style="width: max-content">
            <el-table :data="StandardList" border style="width:max-content">
                <el-table-column fixed prop="name" label="器材名称" width="150"></el-table-column>
                <el-table-column fixed prop="price" label="器材价格" width="150"></el-table-column>
                <el-table-column label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button @click="Modify(scope.row)" size="small" type="primary">修改</el-button>
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
                Equipment:{
                    name:'',
                    price:6,
                },
                StandardList:[],
                total:null,
            }
        },
        methods:{
            Modify(row){
                this.$router.push({
                    path: '/equipmentstandardedit',
                    query:{
                        name:row.name,
                        status:true
                    }
                })
            },
            Add(){
                this.$router.push({
                    path: '/equipmentstandardedit',
                    query:{
                        status:false
                    }
                })
            },
            Page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/equipment/findAllStandard/"+currentPage+"/5";
                axios.get(url).then(function (resp) {
                    _this.StandardList = resp.data.content;
                    _this.total = resp.data.totalElements;
                })
            }
        },
        created() {
            const _this = this;
            const url = "http://localhost:8181/equipment/findAllStandard/1/5";
            axios.get(url).then(function (resp) {
                _this.StandardList = resp.data.content;
                _this.total = resp.data.totalElements;
            })
        }
    }
</script>

<style scoped>

</style>
