<template>
    <div>
        <el-form>
            <el-form-item>
                 <el-input v-model="place.placename" placeholder="请输入2~5位的中文名称"
                           style="width: 500px;padding-right: 10px;padding-left: 130px"></el-input>
                <el-button type="primary" @click="sure">添加场地</el-button>
            </el-form-item>
        </el-form>
        <el-dialog title="系统消息" :visible.sync="dialogFormVisible" :show-close="true">
            <span> 确认是否添加{{place.placename}}场地</span>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="add">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>
        <el-form>
            <el-form-item>
                <el-input v-model="place1.placename" placeholder="请输入2~5位的中文名称"
                          style="width: 250px;padding-right: 10px;padding-left: 130px"></el-input>
                <el-input v-model="place1.placenumber" placeholder="请输入1~2位的场地号"
                          style="width: 250px;padding-right: 10px;"></el-input>
                <el-button type="primary" @click="sure1">删除场地</el-button>
            </el-form-item>
        </el-form>
        <el-dialog title="系统消息" :visible.sync="dialogFormVisible1" :show-close="true">
            <span> 确认是否删除{{place1.placename}}{{place1.placenumber}}场地</span>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="deleteplace">确认</el-button>
                <el-button plain @click="cancel">取消</el-button>
            </div>
        </el-dialog>

        <div style="width: 700px;margin-left: 100px">
            <el-table
                    :data="places"
                    border
                    style="width: 700px;">
                <el-table-column
                        prop="id"
                        label="所有场地"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="placename"
                        label="场地名称"
                        width="300">
                </el-table-column>
                <el-table-column
                        prop="placenumber"
                        label="场地号"
                        width="300">
                </el-table-column>
            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="6"
                    :total="total"
                    @current-change="page"
                    style="text-align: center">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                place:{placename:''},
                formLabelWidth:'20px',
                dialogFormVisible:false,
                place1:{placename:'',placenumber:''},
                places:[],
                total:'',
                dialogFormVisible1:false
            }
        },
        created() {
            const _this = this;
            const url1 = "http://localhost:8181/place/places/1/6";
            axios.get(url1).then(function (resp) {
                _this.places = resp.data.content;
                _this.total=resp.data.totalElements
            })
        },
        methods: {
            page(currentPage) {
                const _this = this;
                const url = "http://localhost:8181/place/places/" + currentPage + "/6";
                axios.get(url).then(function (resp) {
                    _this.places = resp.data.content;
                    _this.total = resp.data.totalElements
                })
            },
            sure() {
                if (this.place.placename === '') {
                    alert("场地名称为空，请重新输入")
                } else if (!this.checkplace(this.place.placename)) {
                    alert("场地名称不匹配，请重新输入");
                } else {
                    this.dialogFormVisible = true;
                }
            },
            checkplace: function (place) {
                let re = /^[\u4E00-\u9FA5]{2,5}/;
                return re.test(place);
            },
            cancel() {
                this.dialogFormVisible = false;
                this.dialogFormVisible1 = false;
            },
            add() {
                const _this = this;
                const url = "http://localhost:8181/place/placeadd";
                axios.post(url, {placename: this.place.placename}).then(function (resp) {
                    if (resp.data === "success") {
                        _this.dialogFormVisible = false;
                        _this.$alert(_this.place.placename + '添加成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                    } else {
                        _this.dialogFormVisible = false;
                        _this.$alert(_this.place.placename + '添加失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            },
            sure1() {
                if (this.place1.placename === '' || this.place1.placenumber === '') {
                    alert("场地名称或场地号为空，请重新输入")
                } else if (!this.checkname(this.place1.placename)) {
                    alert("场地名称不匹配，请重新输入");
                } else if (!this.checknumber(this.place1.placenumber)) {
                    alert("场地号不匹配，请重新输入");
                } else {
                    this.dialogFormVisible1 = true;
                }
            },
            checkname: function (placename) {
                let re = /^[\u4E00-\u9FA5]{2,5}$/;
                return re.test(placename);
            },
            checknumber: function (placenumber) {
                let re = /^\d{1,2}$/;
                return re.test(placenumber);
            },
            deleteplace() {
                const _this = this;
                const url = "http://localhost:8181/place/placedelete";
                axios.post(url, {
                    placename: this.place1.placename,
                    placenumber: this.place1.placenumber
                }).then(function (resp) {
                    if (resp.data === "success") {
                        _this.dialogFormVisible1 = false;
                        _this.$alert(_this.place1.placename + '删除成功!', '消息', {
                            confirmButtonText: '确定',
                        });
                    } else {
                        _this.dialogFormVisible1 = false;
                        _this.$alert(_this.place1.placename + '删除失败!', '消息', {
                            confirmButtonText: '确定',
                        });
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
