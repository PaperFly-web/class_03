// <el-card class=box-card>
//                 <el-row :gutter=24>
//                     <el-col :span=8><el-radio v-model=itemId label=1>备选项</el-radio></el-col>
//                     <el-col :span=8><h3>{{proShopeName}}</h3></el-col>
//                     <el-col :span=8><el-button type=danger>删除</el-button></el-col>
//                   </el-row> +
//                   <el-row :gutter=24>
//                     <el-col :span=8><el-image
//                         style="width: 100px; height: 100px"
//                         :src="imgUrl"
//                         fit="fill"></el-image></el-col>
//                     <el-col :span="8"><span>名称：{{name}}</span><br><span>类型：{{type}}</span><br><span>单价：{{price}}</span></el-col>
//                     <el-col :span="8"> <el-input-number min="1" v-model="num" :step="1"></el-input-number></el-col>
//                   </el-row>
//               </el-card>