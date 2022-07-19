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
			<el-form-item label="设备类型" prop="deviceType">
			  <el-select
			    v-model="queryParams.deviceType"
			    placeholder="设备类型"
			    clearable
			    style="width: 240px"
			  >
			    <el-option
			      v-for="dict in dict.type.device_type"
			      :key="dict.value"
			      :label="dict.label"
			      :value="dict.value"
			    />
			  </el-select>
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

      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['device:monitor:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

			<el-table v-loading="loading" :data="deviceList" >
        <el-table-column type="selection" width="55" align="center" />
				<el-table-column label="设备编号" width="95"  align="center" prop="deviceId"   />
				<el-table-column label="设备名称" width="80"  align="center" prop="deviceName" />
				<el-table-column label="设备类型" width="140" align="center" prop="deviceType" />
        <el-table-column label="图片名称" width="125" align="center" prop="imageName"  />
        <el-table-column label="图片类型" width="80"  align="center" prop="imageType"  />
        <el-table-column label="图片大小" width="80"  align="center" prop="imageSize"  />
        <el-table-column label="仪表数值" width="80"  align="center" prop="imageResult"/>
        <el-table-column label="图片链接" width="350" align="center" prop="imageUrl"   show-overflow-tooltip >
          <template slot-scope="scope">
            <a :href="scope.row.imageUrl" target="_blank" class="el-button--text">{{ scope.row.imageUrl }}</a>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" width="160" align="center" prop="createTime" >
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

		</el-form>
	</div>
</template>

<script>
  import {listDevice} from "@/api/robot/device";

export default {
	name: "device",
  dicts: ['device_type', 'image_type'],
	data() {
		return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 菜单表格树数据
      deviceList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceName: undefined,
        deviceType: undefined,
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
    /** 查询仪表信息列表 */
    getList() {
      this.loading = true;
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.$confirm('是否确认导出所有用户数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.download('device/monitor/export', {
          ...this.queryParams
        }, `device_${new Date().getTime()}.xlsx`)
      }).catch(() => {});
    }
  }
}
</script>

<style>
</style>
