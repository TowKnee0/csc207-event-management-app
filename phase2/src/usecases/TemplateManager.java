package usecases;

import gateways.IGateway;
import entities.FieldSpecs;
import entities.Template;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TemplateManager {
    /**
     * Manages templates in the system
     */
    // === Class Variables ===
    private List<Template> templateList;
    private IGateway<Template> gateway;

    // === Methods ===
    public TemplateManager(IGateway<Template> gateway) {
        this.gateway = gateway;
        templateList = gateway.getAllElements();
    }

    public boolean editTemplateName(String templateName, String newName){
        this.retrieveTemplateByName(templateName).setTemplateName(newName);
        return true;
    }

    public boolean checkNameUniqueness(String newName){
        // Note: this will return false even if the new name is the same as the previous name.
        List<String> templateNames = new ArrayList<>();
        for (Template template : templateList){
            templateNames.add(template.getTemplateName());
        }
        return !templateNames.contains(newName);
    }

    public List<String> returnTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        for (Template template : templateList) {
            templateNames.add(template.getTemplateName());
        }
        return templateNames;
    }

    public Template retrieveTemplateByName(String templateName){
        List<Template> holderList = new ArrayList<>();
        for (Template template : templateList) {
            if (template.getTemplateName().equals(templateName)) {
                holderList.add(template);
            }
        }
        return holderList.remove(0);
    }

    public List<Template> getTemplateList() {
        return templateList;
    }

    public List<FieldSpecs> getFieldSpecs(String templateName){
        return this.retrieveTemplateByName(templateName).getFieldDescriptions();
    }

    public void saveAllTemplates() {
        gateway.saveAllElements(templateList);
    }

    public String createTemplate(String templateName){
        List<FieldSpecs> emptyFieldSpecs = new ArrayList<>();
        Template template = new Template(emptyFieldSpecs, templateName);
        return template.getTemplateName();
    }

    public FieldSpecs createNewFieldSpecs(String inputFieldName, String inputDataType, String inputIsRequired) {
        Class<?> convertedDataType = null;
        boolean convertedIsRequired = false;
        if (inputDataType.equalsIgnoreCase("string")){
            convertedDataType = String.class;
        }
        else if (inputDataType.equalsIgnoreCase("boolean")){
            convertedDataType = Boolean.class;
        }
        else if (inputDataType.equalsIgnoreCase("int")){
            convertedDataType = Integer.class;
        }
        else if (inputDataType.equalsIgnoreCase("localdatetime")){
            convertedDataType = LocalDateTime.class;
        }
//        else if (inputDataType.equalsIgnoreCase("list<string>")){
//            Object convertedDataType = List<String>.class;;
//        }

        if (inputIsRequired.equalsIgnoreCase("yes")){
            convertedIsRequired = true;
        }

        return new FieldSpecs(inputFieldName, convertedDataType, convertedIsRequired);
    }


    public void addFieldSpecs(String templateName, FieldSpecs fieldSpecs){
        for (Template template: templateList){
            if(template.getTemplateName().equals(templateName)){
                template.addFieldSpecs(fieldSpecs);
            }
        }
    }

    public void deleteTemplate(String templateName){
        for (Template template: templateList){
            if(template.getTemplateName().equals(templateName)){
                templateList.remove(retrieveTemplateByName(templateName));
            }
        }
    }
    public void deleteFieldSpecs(String templateName, String fieldName){
        for (Template template: templateList){
            if(template.getTemplateName().equals(templateName)){
                for (FieldSpecs fieldSpecs: template.getFieldDescriptions()){
                    if (fieldSpecs.getFieldName().equals(fieldName)){
                        template.getFieldDescriptions().remove(fieldSpecs);
                    }
                }
            }
        }
    }
}
