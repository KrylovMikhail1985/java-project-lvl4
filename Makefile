run-dist:
	APP_ENV=development		./build/install/app/bin/app

clean:
	./gradlew clean

build:
	./gradlew clean installDist

start:
	APP_ENV=development	./gradlew run

test:
	./gradlew test
check:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

.PHONY: build