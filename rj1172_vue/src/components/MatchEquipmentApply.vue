<template>
    <!--    赛事器材申请-->
    <div>
        <el-form :label-position="labelPosition" label-width="100px" >
            <el-form-item label="赛事ID">
                <el-input v-model="talbeList.gameid"></el-input>
            </el-form-item>
            <el-form-item label="申请器材名">
                <el-input v-model="talbeList.gametoolname"></el-input>
            </el-form-item>
            <el-form-item label="申请时间">
                <el-date-picker
                        v-model="applyTime"
                        type="date"
                        placeholder="选择日期">
                </el-date-picker>
                <!--                开始时间-->
                <el-time-select
                        placeholder="起始时间"
                        v-model="starttime"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '18:30'
                 }">
                </el-time-select>
                <!--                结束时间-->
                <el-time-select
                        placeholder="结束时间"
                        v-model="endtime"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '18:30',
                        minTime: talbeList.starttime
                 }">
                </el-time-select>
            </el-form-item>
            <el-form-item label="申请数量">
                <el-input-number v-model="applyNum" @change="handleChange" :min="0" :max="500" label="申请器材"></el-input-number>
            </el-form-item>
        </el-form>

        <el-button type="primary" round @click.native="submit">确定</el-button>
        <el-button type="info" round>取消</el-button>
    </div>
</template>

<script>
    export default {
        name: "MatchEquipmentApply",
        data(){
            return {
                labelPosition: 'right',
                applyNum:0,
                applyTime:'',
                starttime:'',
                endtime:'',

                talbeList:{
                    gametoolname:'',
                    gameid:'',
                }
            }
        },
        methods:{
            submit(){
                if(this.talbeList.gameid == ''||this.talbeList.gametoolname == ''||this.applyTime==''||this.starttime == ''||this.endtime == ''){
                    this.$message.error('没有填写完毕！！');
                }else {
                    const _this  = this;
                    axios.post('http://localhost:8181/gameTool/toolAdd',this.talbeList).then(function (resp) {
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
            }
        }
    }
</script>

<style scoped>

</style>
