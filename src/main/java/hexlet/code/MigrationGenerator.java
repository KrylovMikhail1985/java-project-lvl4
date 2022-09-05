package hexlet.code;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;
import java.io.IOException;

public final class MigrationGenerator {
    public static void main(String[] args) throws IOException {
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUsername("sa");
//        dataSourceConfig.setPassword("");
//        dataSourceConfig.setUrl("jdbc:h2:mem:myapp;");
//
//// configuration ...
//        DatabaseConfig config = new DatabaseConfig();
//        config.setDataSourceConfig(dataSourceConfig);
//
//// create database instance
//        Database database = DatabaseFactory.create(config);


        // Создаём миграцию
        DbMigration dbMigration = DbMigration.create();
        // Указываем платформу, в нашем случае H2
        dbMigration.addPlatform(Platform.H2, "h2");
//        dbMigration.addPlatform(Platform.POSTGRES, "postgres");
        // Генерируем миграцию
        dbMigration.generateMigration();
    }
}
// By default, the generated migrations go into src/main/resources/dbmigration