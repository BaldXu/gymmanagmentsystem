<template>
    <div>
        <el-table
                :data="orders"
                border
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label=""
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="placename"
                    label="场地名称"
                    width="180" >
            </el-table-column>
            <el-table-column
                    prop="placenumber"
                    label="场地号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="orderdate"
                    label="使用日期"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="begintime"
                    label="开始时间"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="endtime"
                    label="结束时间"
                    width="200">
            </el-table-column>
        </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                page-size="8"
                :total="total"
                @current-change="page"
                style="text-align: center">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                orders:[],
                total:''
            }
        },
        created() {
            const _this = this;
            const url1 = "http://localhost:8181/place/postorders/1/8";
            axios.get(url1,{params:{userpost:"老师"}}).then(function (resp) {
                _this. orders = resp.data.content;
                _this.total=resp.data.totalElements
            });
        },
        methods:{
            page(currentPage){
                const _this = this;
                const url = "http://localhost:8181/place/postorders/"+currentPage+"/8";
                axios.get(url,{params:{userpost:"老师"}}).then(function (resp) {
                    _this. orders = resp.data.content;
                    _this.total=resp.data.totalElements
                })
            },
        }
    }
</script>

<style scoped>

</style>
