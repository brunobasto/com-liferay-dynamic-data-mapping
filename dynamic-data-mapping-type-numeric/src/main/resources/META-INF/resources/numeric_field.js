AUI.add(
	'liferay-ddm-form-field-numeric',
	function(A) {
		new A.TooltipDelegate(
			{
				position: 'left',
				trigger: '.liferay-ddm-form-field-numeric .help-icon',
				triggerHideEvent: ['blur', 'mouseleave'],
				triggerShowEvent: ['focus', 'mouseover'],
				visible: false
			}
		);

		var NumericField = A.Component.create(
			{
				ATTRS: {
					dataType: {
						value: 'integer'
					},

					placeholder: {
						state: true,
						value: ''
					},

					predefinedValue: {
						value: ''
					},

					type: {
						value: 'numeric'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-numeric',

				prototype: {
					initializer: function() {
						var instance = this;

						instance.after('render', instance._afterNumericFieldRender, instance);

						instance.evaluate = A.debounce(
							function() {
								NumericField.superclass.evaluate.apply(instance, arguments);
							},
							300
						);
					},

					getChangeEventName: function() {
						return 'input';
					},

					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							NumericField.superclass.getTemplateContext.apply(instance, arguments),
							{
								predefinedValue: instance.get('predefinedValue')
							}
						);
					},

					getValue: function() {
						var instance = this;

						var inputNode = instance.getInputNode();

						var value = inputNode.val();

						if (value === '') {
							return value;
						}

						var dataType = instance.get('dataType');

						if (dataType === 'integer') {
							return parseInt(value, 10);
						}

						return parseFloat(value);
					},

					showErrorMessage: function() {
						var instance = this;

						NumericField.superclass.showErrorMessage.apply(instance, arguments);

						var container = instance.get('container');

						var inputGroup = container.one('.input-group-container');

						inputGroup.insert(container.one('.form-feedback-indicator'), 'after');
					},

					_afterNumericFieldRender: function() {
						var instance = this;

						var inputMask = new DDMNumeric.Inputmask(
							'decimal',
							{
								groupSeparator: ',',
								radixPoint: '.'
							}
						);

						inputMask.mask(instance.getInputNode().getDOM());

						return instance;
					}
				}
			}
		);

		Liferay.namespace('DDM.Field').Numeric = NumericField;
	},
	'',
	{
		requires: ['aui-autosize-deprecated', 'aui-tooltip', 'liferay-ddm-form-renderer-field']
	}
);