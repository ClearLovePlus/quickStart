package com.quick.api;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-19 14:41
 */
public class GenerateTable {
    public static void main(String[] args){
        String path = System.getProperty("user.dir")+"\\dap-push-api\\src\\main\\java";
        String tableName = "metadata_authorize";
        List<String> tables = Arrays.asList("t_push_type","t_push_type_title","t_push_type_para","t_push_para_dict","t_push_white_list");
        boolean ifCreateService = true;
        boolean ifCreateMapperInterface = true;
        boolean ifCreateMapperXml = true;
        String url = "jdbc:mysql://172.17.66.93:3306/dap_push?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&";
        String username = "sitdbuser";
        String sec = "Topsperity54@sit";

        FastAutoGenerator.create(url, username, sec)
                .globalConfig(builder -> {
                    builder.author("system") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(path); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.TINYINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> builder.parent("com.topsperity.push")
                        .mapper("repository.mapper")
                        .service("repository")
                        .serviceImpl("repository.impl")
                        .entity("repository.mapper.po"))
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix().build()
                            .serviceBuilder()
                            .formatServiceFileName("%sRepository")
                            .formatServiceImplFileName("%sRepositoryImpl")
                            .fileOverride()
                            .entityBuilder()
                            .enableColumnConstant()
                            .enableLombok()
                            .fileOverride()
                            .mapperBuilder()
                            .fileOverride();
                })
                .templateConfig(builder -> {
                    if (!ifCreateService){
                        builder.service(null).serviceImpl(null);
                    }
                    if (!ifCreateMapperInterface){
                        builder.mapper(null);
                    }
                    if (!ifCreateMapperXml){
                        builder.xml(null);
                    }
                    builder.controller(null);
                })
                .execute();
    }
}
