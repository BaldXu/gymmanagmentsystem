<template>
<!--    赛事取消-->
    <div>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column label="赛事ID" width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.gameid }}</span>
                </template>
            </el-table-column>
            <el-table-column label="赛事名称" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>负责人: {{ scope.row.gameres }}</p>
                        <p>赛事日期: {{ scope.row.gametime }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.gamename }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="deleteGameee(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary">提交</el-button>
        <el-button type="info">取消</el-button>

    </div>
</template>

<script>
    export default {
        name: "MatchCancel",

        data() {
            return {
                labelPosition: 'right',
                tableData: []
            }
        },
        methods: {
            deleteGameee(row) {
                const _this = this;
                var quqding = confirm("确定删除？");
                if (quqding) {
                    axios.delete('http://localhost:8181/gameData/deleteById/' + row.gameid).then(function (resp) {
                        _this.$alert("删除成功！", '消息', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.reload();
                            }
                        });
                    })
                } else {
                    _this.$message('已取消');
                }
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/gameData/findAll').then(function (resp) {
                console.log(resp.data)
                _this.tableData = resp.data
            })
        }
    }
</script>

<style scoped>

</style>
