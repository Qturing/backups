from spiderNews import spidernews
import readurl

# website = 'sinaents'
websites = ['sinanews','sinaents']
for website in websites:
    results = readurl.readtxt(website)
    count = 1
    for url in results:
        spidernews(url,str(count),website)
        count = count + 1
print("All Done!")