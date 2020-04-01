-- из скайпа способ дабы автоматизировать скачивание и подключение хром драйвера

Привет. Ещё такой способ есть: добавить в pom 
<!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>3.6.0</version>
        </dependency>
А в BeforeEach указать: WebDriverManager.chromedriver().setup(); (либо firefoxDriver() или для других браузеров). А из папки драйвер удалить и System.setProperty тоже удалить ввобще. Драйвер сам автоматом скачается для нужного браузера. Кирилл, одобряешь такой способ?


-- способ как заставить мавен убрать баг идее о том что она постоянно выставляет версию байткода на 1.5

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
     </build>
	 
	 or 
	 
	<properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
	</properties>