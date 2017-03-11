//以下为修改jQuery Validation插件兼容Bootstrap的方法，没有直接写在插件中是为了便于插件升级
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                element.closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    //error.appendTo(element.parent().parent().parent());
                    error.appendTo(element.parent().parent());
                } else {
                    error.appendTo(element.parent().parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"
        });


        $.fn.extend({

            form_validate:function(){
                var _obj = $(this);
                if(!_obj.is('form')){
                    return;    
                }

                var v_rules = {}, v_messages = {};
                
                $('input,select,textarea').each(function(){

                    var v = null, vm = null;

                     eval("v = " + $(this).attr("validate"));
                     if(v)
                     {
                        v_rules[$(this).attr('name')] = v;
                     }

                      eval("vm = " + $(this).attr("validateMessage"));

                      if(vm)
                      {
                        v_messages[$(this).attr('name')]  = vm;
                      }

                });

                _obj.validate({
                    rules:    v_rules,
                    messages: v_messages
                });
            }

        });
        

        // $(document).ready(function($) {
            
        // });



        $(function(){
            $('#signupForm').form_validate();
        });

