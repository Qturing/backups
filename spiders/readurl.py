import csv
def readtxt(file):
    url = []
    with open('D:\\qxf\\Temp\\'+ file +'.csv','r') as f:
        reader = csv.reader(f)
        for line in reader:
                if len(line) == 2:
                        url.append(line[1].strip())
    return url