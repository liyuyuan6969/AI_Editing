# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.

import tornado.ioloop
import tornado.web
import jieba


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        text = self.get_argument("text", default=None)
        response = ""
        print(text)
        words = jieba.cut(text, use_paddle=True)
        # search_words(words)
        if text is not None:
            response += f" Your text is {text}."
        print(response)
        self.write(response)

def search_word(words):
    # TODO
    # for w in words:
    return



def make_app():
    return tornado.web.Application([
        (r"/text", MainHandler),
    ])


if __name__ == "__main__":
    app = make_app()
    app.listen(8888)
    tornado.ioloop.IOLoop.current().start()

