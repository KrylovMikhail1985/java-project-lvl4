run-dist:
	./build/install/app/bin/app

build:
	./gradlew clean installDist

test:
	./gradlew test
check:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

.PHONY: build