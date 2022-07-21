run-dist:
	./build/install/app/bin/app

build:
	./gradlew clean install

test:
	./gradlew test
check:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

.PHONY: build