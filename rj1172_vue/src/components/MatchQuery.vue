<template>
    <!--    赛事查询-->
    <div>
        <el-form :label-position="labelPosition" label-width="100px" >
            <el-form-item label="赛事ID">
                <el-input v-model="inputId" type='number'></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" round @click.native="submit">确定</el-button>
        <el-button type="info" round @click.native = "reset">重置</el-button>
<!--        弹窗-->
        <el-dialog title="赛事信息" :visible.sync="dialogTableVisible1" width="1000px">
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
                <el-form-item label="赛事ID">
                    <span>{{id}}</span>
                </el-form-item>
                <el-form-item label="赛事名称">
                    <span>{{name}}</span>
                </el-form-item>
                <el-form-item label="赛事时间">
                    <span>{{time}}</span>
                </el-form-item>
                <el-form-item label="赛事地点">
                    <span>{{place}}</span>
                </el-form-item>
                <el-form-item label="负责人">
                <span>{{res}}</span>
            </el-form-item>
                <el-form-item label="公告">
                    <span>{{intro}}</span>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "MatchQuery",
        data(){
            return {
                labelPosition: 'right',
                inputId:'',
                dialogTableVisible1:false,
                //
                id:'null',
                name:'null',
                res:'null',
                time:'null',
                place:'null',
                intro:'null'
            }
        },
        methods:{
            submit(){
                var ll = this.inputId;
                this.reset();
                this.inputId = ll;
                if(this.inputId == ''){
                    this.$message.error('没有填写ID！！')
                }else {
                    this.dialogTableVisible1 = true;
                    const _this= this;
                    axios.get('http://localhost:8181/gameData/findById/'+this.inputId).then(function (resp) {
                        _this.id = resp.data.gameid;
                        _this.name = resp.data.gamename;
                        _this.res = resp.data.gameres;
                        _this.time = resp.data.gametime;
                        _this.place =resp.data.gameplace;
                        _this.intro = resp.data.intro;
                    })
                }
            },
            reset(){
                if(this.inputId == ''){
                    this.$message('不需要重置');
                }else {
                    this.id = '';
                    this.name='';
                    this.place='';
                    this.time='';
                    this.res='';
                    this.inputId = '';
                    this.intro = '';

                    this.$message({
                        message: '重置成功！！',
                        type: 'success'
                    });
                }
            }
        },
    }
</script>

<style scoped>

</style>
