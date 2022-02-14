package br.ufrj.agilehealth;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.ufrj.agilehealth");

        noClasses()
            .that()
            .resideInAnyPackage("br.ufrj.agilehealth.service..")
            .or()
            .resideInAnyPackage("br.ufrj.agilehealth.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.ufrj.agilehealth.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
