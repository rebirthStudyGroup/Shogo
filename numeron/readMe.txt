�y�Q�[�����[���z
3�P�^�̂P�`�X�܂ł̐���𓖂Ă�Q�[���B
�`�����X��10��(very easy)�B
�E���̂P�`�X�܂ł̐�����I�сA���܂�����CALL�{�^���A
�O�̐������C���������ꍇ�́ARESET�{�^���������B
3�P�^�S�ē��͂�����ɁACALL�{�^���������ƁA���ʔ���B

EAT�E�E�E���͂��������̒l�Əꏊ�������ƈ�v���Ă��鐔
BITE�E�E�E���͂��������̒l�݂̂������ƈ�v���Ă��鐔
�@�@�@�@�@(EAT�Ɋ܂܂�鐔���͑ΏۊO)


�y������z
Python 3.7
Kivy 1.10.1

�y�C���K�{�ӏ��z
Lib/site-packages/kivy/lang/builder.py

���ύX�O
����START
    def load_file(self, filename, **kwargs):
        '''Insert a file into the language builder and return the root widget
        (if defined) of the kv file.
        :parameters:
            `rulesonly`: bool, defaults to False
                If True, the Builder will raise an exception if you have a root
                widget inside the definition.
        '''
        filename = resource_find(filename) or filename
        if __debug__:
            trace('Lang: load file %s' % filename)
        with open(filename, 'r') as fd:
            kwargs['filename'] = filename
            data = fd.read()

            # remove bom ?
            if PY2:
                if data.startswith((codecs.BOM_UTF16_LE, codecs.BOM_UTF16_BE)):
                    raise ValueError('Unsupported UTF16 for kv files.')
                if data.startswith((codecs.BOM_UTF32_LE, codecs.BOM_UTF32_BE)):
                    raise ValueError('Unsupported UTF32 for kv files.')
                if data.startswith(codecs.BOM_UTF8):
                    data = data[len(codecs.BOM_UTF8):]

        return self.load_string(data, **kwargs)
����END

���ύX��
����START
def load_file(self, filename, encoding=None, **kwargs):
        '''Insert a file into the language builder and return the root widget
        (if defined) of the kv file.

        :parameters:
            `rulesonly`: bool, defaults to False
                If True, the Builder will raise an exception if you have a root
                widget inside the definition.

            `encoding`: File charcter encoding. Defaults to utf-8,
                if not given, and utf-8 yields an encoding error, attempts
                loading the file as the native system encoding as
                given by `sys.getdefaultencoding()`
        '''
        filename = resource_find(filename) or filename
        if __debug__:
            trace('Lang: load file %s' % filename)
        if encoding is None:
            encoding_given = False
            encoding = 'utf-8'
        else:
            encoding_given = True

        kwargs['filename'] = filename
        try:
            with open(filename, 'r', encoding=encoding) as fd:
                data = fd.read()
        except UnicodeDecodeError:
            if encoding_given:
                # Don't try to guess encoding if it was passed explicitly
                raise
            Logger.warning(
                'File "{0}" failed to open with  "utf-8" codec. '
                'Trying native system encoding now. Note that '
                'this may cause text differences when the project '
                'is run in other systems'.format(filename)
            )
            with open(filename, 'r') as fd:
                data = fd.read()

            # remove bom ?
            if PY2:
                if data.startswith((codecs.BOM_UTF16_LE, codecs.BOM_UTF16_BE)):
                    raise ValueError('Unsupported UTF16 for kv files.')
                if data.startswith((codecs.BOM_UTF32_LE, codecs.BOM_UTF32_BE)):
                    raise ValueError('Unsupported UTF32 for kv files.')
                if data.startswith(codecs.BOM_UTF8):
                    data = data[len(codecs.BOM_UTF8):]

        return self.load_string(data, **kwargs)    # �C���f���g����炷

����END