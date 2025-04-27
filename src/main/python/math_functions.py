import math

def linmod(x, m, b):
    return m * x + b

def calcYprediction_powerlaw(x, b, c):
    return (2**c) * (x**b)

def calcLogYprediction(slope, c, x):
    y_pred = slope * math.log2(x) - c
    return y_pred

def calcLog2_c(x2, y2, slope):
    return math.log2(y2) - (slope*(math.log2(x2)))

def calcSlopeLog(c1, c2):
    x0, y0 = c1
    x1, y1 = c2
    if (x0 == 0 and x1 == 0): return (math.log2(y1) - math.log2(y0))
    # print(str(math.log2(y1)) + "-" + str(math.log2(y0)) + "/" + str(math.log2(x1)) + "-" + str(math.log2(x0)))
    return (math.log2(y1) - math.log2(y0)) / (math.log2(x1) - math.log2(x0))

def calcSlope(c1, c2):
    x0, y0 = c1
    x1, y1 = c2
    if (x0 == 0 and x1 == 0): return (y1 - y0)
    return (y1 - y0) / (x1 - x0)