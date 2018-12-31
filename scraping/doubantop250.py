import requests
from bs4 import BeautifulSoup
def get_movies():
    movie_list = []
    movie = {}
    for i in range(10):
        link = 'https://movie.douban.com/top250?start=' + str(i*25)
        r = requests.get(link,timeout=10)
        print(str(i+1), "status code:", r.status_code)
        soup = BeautifulSoup(r.text,"html.parser")
        div_list = soup.find_all('div',class_='info')
        for each in div_list:
            movie['name'] = each.find('div', class_='hd').a.span.text.strip()
            info =  each.find('div', class_='bd').p.text.strip().split("\n")
            cast = info[0].split(': ')
            director = cast[1].split()[0]
            movie['director'] = director
            other = info[1].split('/')
            year = other[0]
            country = other[1]
            movie_type = other[2]
            # movie['info'] = each.find('div', class_='bd').p.text.strip()
            movie['cast'] = cast
            movie['year'] = year
            movie['country'] = country
            movie['movie_type'] = movie_type
            movie['star'] = each.find('span', class_='rating_num').text.strip()
            # movie['quote'] = each.find('span', class_='inq').text.strip()
            tmp = movie.copy()
            movie_list.append(tmp)
    return movie_list


movies = get_movies()

for i in range(len(movies)):
    print(i+1,movies[i]['name'], movies[i]['director'])