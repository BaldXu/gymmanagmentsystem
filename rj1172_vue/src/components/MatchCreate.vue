<template>
<!--    赛事创建-->
    <div>
        <el-form :label-position="labelPosition" label-width="100px" >
            <el-form-item label="负责人">
                <el-input v-model="talbeList.gameres"></el-input>
            </el-form-item>
            <el-form-item label="赛事名称">
                <el-input v-model="talbeList.gamename"></el-input>
            </el-form-item>
            <el-form-item label="申请时间">
                <el-date-picker
                        v-model="talbeList.gametime"
                        type="date"
                        format=" MM 月 dd 日"
                        value-format="MM月dd日"
                        placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="赛事介绍">
                <el-input
                        type="textarea"
                        :rows="2"
                        placeholder="请输入内容"
                        v-model="talbeList.intro">
                </el-input>
            </el-form-item>
        </el-form>

        <el-button type="primary" round @click.native="submit">确定</el-button>
        <el-button type="info" round @click.native="reset">重置</el-button>
    </div>
</template>

<script>
    export default {
        name: "MatchCreate",
        data(){
            return {
                labelPosition: 'right',
                applyTime:'',
            //
                talbeList:{
                    gameres:'',
                    gamename:'',
                    gametime:'',
                    intro:'',
                }
            }
        },
        methods:{
            submit(){
                if(this.talbeList.gametime==''||this.talbeList.gameres==''||this.talbeList.gamename==''||this.talbeList.intro==''){
                    this.$message.error('没有填写完毕！！');
                }else {
                    const _this  = this;
                    axios.post('http://localhost:8181/gameData/gameCreate',this.talbeList).then(function (resp) {
                        if(resp.data == 'success'){
                            _this.$message({
                                message: '添加成功！！',
                                type: 'success'
                            });
                        }else {
                            _this.$message.error('未知原因添加失败');
                        }
                    })
                }
            },
            show(){
                console.log(this.applyTime);
            },
            reset(){
                this.talbeList.gameres='';
                this.talbeList.gamename='';
                this.talbeList.gametime='';
                this.talbeList.intro='';

                this.$message({
                    message: '重置成功！！',
                    type: 'success'
                });
            }
        }
    }
</script>

<style scoped>

</style>
