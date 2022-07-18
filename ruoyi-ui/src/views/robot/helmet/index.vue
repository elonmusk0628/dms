<template>
	<div class="app-container">
		<el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
			<el-form-item label="设备名称" prop="deviceName">
			  <el-input
			    v-model="queryParams.deviceName"
			    placeholder="请输入设备名称"
			    clearable
			    style="width: 240px"
			    @keyup.enter.native="handleQuery"
			  />
			</el-form-item>
			<el-form-item label="图片名称" prop="imageName">
			  <el-input
			    v-model="queryParams.imageName"
			    placeholder="请输入图片名称"
			    clearable
			    style="width: 240px;"
			    @keyup.enter.native="handleQuery"
			  />
			</el-form-item>
			<el-form-item label="图片类型" prop="imageType">
			  <el-select
			    v-model="queryParams.imageType"
			    placeholder="图片类型"
			    clearable
			    style="width: 240px"
			  >
			    <el-option
			      v-for="dict in dict.type.image_type"
			      :key="dict.value"
			      :label="dict.label"
			      :value="dict.value"
			    />
			  </el-select>
      </el-form-item>

			<el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>

			<el-table v-loading="loading" :data="helmetList">
				<el-table-column label="设备编号" width="95"  align="center" prop="deviceId" />
				<el-table-column label="设备名称" width="80"  align="center" prop="deviceName"/>
				<el-table-column label="图片名称" width="140" align="center" prop="imageName"/>
				<el-table-column label="图片类型" width="80"  align="center" prop="imageType"/>
				<el-table-column label="图片大小" width="80"  align="center" prop="imageSize"/>
        <el-table-column label="图片链接" width="750" align="center" prop="imageUrl"/>
        <el-table-column label="创建时间" width="160" align="center" prop="createTime" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>
		</el-form>
	</div>
</template>

<script>
  import {listHelmet} from "@/api/robot/helmet";

export default {
	name: 'helmet',
  dicts: ['device_type', 'image_type'],
	data() {
		return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 菜单表格数据
      helmetList: [],
      // 查询条件
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceName: undefined,
        imageName: undefined,
        imageType: undefined
      }
    }
	},
  created() {
    this.getList();
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 查询安全帽信息列表 */
    getList() {
      this.loading = true;
      listHelmet(this.queryParams).then(Response =>{
        this.helmetList = Response.rows;
        this.total = Response.total;
        this.loading = false;
      })
    }




  }
}
</script>

<style>
</style>
